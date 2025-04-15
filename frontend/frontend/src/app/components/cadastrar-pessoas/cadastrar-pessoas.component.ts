import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PessoaService } from '../../services/pessoa.service';
import { Pessoa } from '../../models/pessoa.model';
import { FormsModule } from '@angular/forms';
import { formatarCpfInput } from '../../util/cpf-utils';
import { formatarTelefoneInput } from '../../util/telefone-utils';

@Component({
  selector: 'app-cadastrar-pessoas',
  imports: [CommonModule, FormsModule],
  templateUrl: './cadastrar-pessoas.component.html',
  styleUrl: './cadastrar-pessoas.component.css'
})
export class CadastrarPessoasComponent implements OnInit {
  
  info!: Boolean;
  success!: Boolean;
  warning!: Boolean;
  danger!: Boolean;
  nome!: string;
  cpf!: string;
  telefone!: string;
  email!: string;
  id!: string;  
  pessoas: Pessoa[]=[];
  msg!: String;

  constructor(
    private pessoaService: PessoaService,    
  ){}
  ngOnInit(): void {
    this.carregarListaDePessoasCadastradas();
  }

  public clickCadastrarPessoa(){
    let pAtualizar;
    let pInserir;
    if (this.id){
      pAtualizar = new Pessoa(this.nome, this.cpf, this.telefone, this.email, Number(this.id));
      this.atualizarPessoa(pAtualizar);
    }else{
      pInserir = new Pessoa(this.nome, this.cpf, this.telefone, this.email);
      this.cadastrarPessoa(pInserir);
    }
    
  }
  
  public cadastrarPessoa(pessoa: Pessoa){
    this.pessoaService.cadastrarPessoa(pessoa).subscribe({
      next: (response) => {
        this.msg = 'Cadastrado com sucesso';
        this.success = true;        
        this.cpf = '';
        this.email = '';
        this.nome = '';
        this.telefone = '';
        this.carregarListaDePessoasCadastradas();
      },
      error: (err) => {
        this.danger = true;
        this.msg = 'Erro ao cadastrar !';
        console.error('Erro ao cadastrar pessoa: ', err);
      }
    });    
  }

  public atualizarPessoa(pessoa: Pessoa){
    this.pessoaService.atualizarPessoa(Number(pessoa.id), pessoa).subscribe({
      next: (response) => {
        this.msg = 'Atualizado com sucesso';
        this.success = true;        
        this.cpf = '';
        this.email = '';
        this.nome = '';
        this.telefone = '';
        this.id = '';
        this.carregarListaDePessoasCadastradas();
      },
      error: (err) => {
        this.danger = true;
        this.msg = 'Erro ao atualizar !';
        console.error('Erro ao atualizar pessoa: ', err);
      }
    });    
  }  

  public carregarListaDePessoasCadastradas(){
    this.pessoaService.listarPessoas().subscribe((result)=>{
      this.pessoas = result;
    });
  }
  
  public clickDeletarPessoa(id: any){
    this.deletarPessoa(id);
  }   

  public deletarPessoa(id: any){
    this.pessoaService.deletarPessoa(id).subscribe({
      next: (response) => {
        this.msg = 'Deletado com sucesso';
        this.success = true;
        this.carregarListaDePessoasCadastradas();
      },
      error: (err) => {
        console.error('Erro ao deletar: ', err);
      }
    }); 
  }
  
  public resetarMsg(){
    this.info = false;
    this.success = false;
    this.warning = false;
    this.danger = false;    
  }

  public onCpfChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.cpf = formatarCpfInput(input.value);
  }

  public onTelefoneChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    this.telefone = formatarTelefoneInput(input.value);
  }

  public clickAtualizarPessoa(id: any){
    this.pessoaService.acharPessoa(id).subscribe({
      next: (response) => {
        console.log(response);
        this.nome = response.nome;
        this.cpf = response.cpf;
        this.telefone = response.telefone;
        this.email = response.email;   
        this.id = response.id!.toString();   
      },
      error: (err) => {
        console.error('Erro ao deletar: ', err);
      }
    });     
  }

  public limparDados(){
    this.cpf = '';
    this.email = '';
    this.nome = '';
    this.telefone = '';
    this.resetarMsg();    
  }

/*   public atualizarPessoa(id: any){
    console.log("ATUALIZAR")
  } */


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
