package com.unipampa.crud.service.impl;

import com.unipampa.crud.config.security.SecurityUtil;
import com.unipampa.crud.entities.Accommodation;
import com.unipampa.crud.enums.AccommodationStats;
import com.unipampa.crud.repository.AccommodationRepository;
import com.unipampa.crud.sender.AccommodationSender;
import com.unipampa.crud.service.AccommodationService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccommodationServiceImpl implements AccommodationService {

	private static final long TAMANHO_MAXIMO_EM_BYTES = 5 * 1024 * 1024; // 5MB
	private static final List<String> TIPOS_SUPORTADOS = List.of("image/jpeg", "image/png", "image/png");
	private static final String ACAO_NAO_AUTORIZADA = "Você não tem permissão para esta ação.";
	public static final String QUANTIDADE_MINIMA_MAXIMA_DE_IMAGENS = "A quantidade de imagens deve ser no mínimo 3 e no máximo 10.";
	public static final String ARQUIVO_NAO_SUPORTADO = "Tipo de arquivo não suportado: ";
	public static final String TAMANHO_MAXIMO_DA_IMAGEM = "Imagem excede o tamanho máximo permitido de 5MB.";
	private AccommodationRepository propertyRepository;
	private AccommodationSender accommodationSender;

	@Value("${app.uploads-dir}")
	private String uploadsDir;

	@Autowired
	public AccommodationServiceImpl(AccommodationRepository repository, AccommodationSender sendMessage) {
		this.propertyRepository = repository;
		this.accommodationSender = sendMessage;
	}

	@Override
	@Transactional
	public void save(Accommodation hosting, MultipartFile[] images) throws IOException {
		List<String> caminhos = buildPathForImages(hosting.getId(), images);
		hosting.setImagesUrls(caminhos);
		Accommodation savedWithImages = propertyRepository.save(hosting);
		accommodationSender.sendMessage(savedWithImages);
	}

	@Override
	public List<Accommodation> findAll() {
		return propertyRepository.findAll();
	}

	@Override
	public void delete(String id) {
		Accommodation property = propertyRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Property not found with id: " + id));
		// Atualiza o status para INATIVO
		property.setStats(AccommodationStats.UNAVAILABLE);

		// Salva novamente no banco de dados
		Accommodation updatedProperty = propertyRepository.save(property);

		// Envia a mensagem para o microsserviço transacional
		accommodationSender.sendMessage(updatedProperty);

	}

	@Override
	public boolean existsByCodAddressAndNumber(String codeAddress, int number) {
		return propertyRepository.existsByZipCodeAndStreetNumber(codeAddress, number);
	}

	@Override
	public Optional<Accommodation> findById(String id) {
		return propertyRepository.findById(id);
	}

	public void validateAuthorizationUser(Optional<Accommodation> accommodation) {
		String authenticatedUserId = SecurityUtil.getAuthenticatedUserId();
		boolean isAdmin = SecurityUtil.isAuthenticatedAdmin();
		if (!isAdmin && !accommodation.get().getHostId().equals(authenticatedUserId)) {
			throw new SecurityException(ACAO_NAO_AUTORIZADA);
		}
	}

	protected List<String> buildPathForImages(String accommodationId, MultipartFile[] images) throws IOException {
		Path pastaImagens = getOrResetImageDirectory(accommodationId);
		validateImages(images);
		return salvarArquivos(images, pastaImagens);
	}


	protected Path getOrResetImageDirectory(String accommodationId) throws IOException {
		Path pastaImagens = Paths.get(uploadsDir + accommodationId);
		if (Files.exists(pastaImagens)) {
			FileSystemUtils.deleteRecursively(pastaImagens);
		}
		Files.createDirectories(pastaImagens);
		return pastaImagens;
	}

	protected void validateImages(MultipartFile[] images) {
		if (images == null || images.length < 3 || images.length > 10) {
			throw new IllegalArgumentException(QUANTIDADE_MINIMA_MAXIMA_DE_IMAGENS);
		}

		for (MultipartFile image : images) {
			String contentType = image.getContentType();
			if (image.getSize() > TAMANHO_MAXIMO_EM_BYTES) {
				throw new IllegalArgumentException(TAMANHO_MAXIMO_DA_IMAGEM);
			}
			if (!TIPOS_SUPORTADOS.contains(contentType)) {
				throw new IllegalArgumentException(ARQUIVO_NAO_SUPORTADO + contentType);
			}
		}
	}

	protected List<String> salvarArquivos(MultipartFile[] images, Path pastaImagens) throws IOException {
		List<String> caminhos = new ArrayList<>();
		for (MultipartFile imagem : images) {
			String nomeArquivo = UUID.randomUUID() + "-" + imagem.getOriginalFilename();
			Path caminhoFinal = pastaImagens.resolve(nomeArquivo);
			Files.copy(imagem.getInputStream(), caminhoFinal, StandardCopyOption.REPLACE_EXISTING);
			caminhos.add(caminhoFinal.toString().replace("\\", "/"));
		}
		return caminhos;
	}

}
