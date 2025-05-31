// Verifica se a collection "user" existe e a limpa se necessário
db.user.drop();

// Verifica se as roles já existem no banco
const hostRole = db.role.findOne({ roleName: "ROLE_GUEST" });
if (!hostRole) {
    throw new Error("Role 'ROLE_GUEST' não encontrada no banco de dados.");
}


// Cria o objeto para o usuário
const user = {
    _id: ObjectId(), // Cria um ObjectId único para o usuário
    userName: "isadora",
    password: "123456", // A senha precisa ser criptografada no backend antes de ser usada
    cpf: "090354949312634423547364354224",
    email: "samuedl@gmfaiflfl.codm.BR",
    name: "string",
    phone: "string",
    type: "ROLE_GUEST", // Associado ao tipo do usuário
    roles: [hostRole._id], // Relacionamento com a role "HOST"
    creationDate: new Date(),
    lastUpdateDate: new Date(),
};

// Insere o usuário no banco
db.user.insert(user);

// Verifica se o usuário foi inserido
print("Usuário criado:");
db.user.find().pretty();