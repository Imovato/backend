// Verifica se a collection roles existe e a limpa se necessário
db.role.drop();

// Define as roles com UUIDs
const roles = [
    {
        _id: "65f3c89d-8f45-4732-9021-f84d559d6a12",
        roleName: "ROLE_HOST"
    },
    {
        _id: "65f3c89d-8f45-4732-9021-f84d559d6a13",
        roleName: "ROLE_GUEST"
    },
    {
        _id: "65f3c89d-8f45-4732-9021-f84d559d6a14",
        roleName: "ROLE_ADMINISTRATOR"
    }
];

// Insere as roles
db.role.insertMany(roles);

// Verifica se foram inseridas
print("Roles criadas:");
db.role.find().pretty();