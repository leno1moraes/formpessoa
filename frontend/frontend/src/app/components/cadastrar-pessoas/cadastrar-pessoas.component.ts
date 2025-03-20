import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { PessoaService } from '../../services/pessoa.service';
import { Pessoa } from '../../models/pessoa.model';
import { FormsModule } from '@angular/forms';
import { ListarPessoasComponent } from '../listar-pessoas/listar-pessoas.component';

@Component({
  selector: 'app-cadastrar-pessoas',
  imports: [CommonModule, FormsModule],
  templateUrl: './cadastrar-pessoas.component.html',
  styleUrl: './cadastrar-pessoas.component.css'
})
export class CadastrarPessoasComponent {
  nome!: string;
  cpf!: string;
  telefone!: string;
  email!: string;

  constructor(
    private pessoaService: PessoaService,    
  ){}

  public clickCadastrarPessoa(){
    let p = new Pessoa(this.nome, this.cpf, this.telefone, this.email);
    this.cadastrarPessoa(p);
  }
  
  public cadastrarPessoa(pessoa: Pessoa){
    this.pessoaService.cadastrarPessoa(pessoa).subscribe({
      next: (response) => {
        console.info("Sucesso: ", response);
        alert('Cadastrada com sucesso!');
        window.location.reload();
      },
      error: (err) => {
        console.error('Erro ao cadastrar pessoa: ', err);
        alert('Erro ao cadastrar');
      }
    });    
  }

/*   public validarDadosAntesDeCadastrar(): boolean{
    if ( !this.nome || this.nome === undefined || this.nome === "")
      return true;

    if ( !this.cpf || this.cpf === undefined || this.cpf === "")
      return true;    

    if ( !this.telefone || this.telefone === undefined || this.telefone === "")
      this.telefone = "";  
    
    if ( !this.email || this.email === undefined || this.email === "")
      this.email = "";

    return false;
  } */

}
