export class Pessoa {
    id?: number;
    nome!: string;
    cpf!: string;
    telefone!: string;
    email!: string;
  
    constructor(nome: string, cpf: string, telefone: string, email: string, id?: number) {
      this.nome = nome;
      this.cpf = cpf;
      this.telefone = telefone;
      this.email = email;
      this.id = id;
    }
  }
  