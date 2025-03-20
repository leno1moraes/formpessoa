import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { PessoaService } from '../../services/pessoa.service';
import { Pessoa } from '../../models/pessoa.model';

@Component({
  selector: 'app-listar-pessoas',
  imports: [CommonModule],
  templateUrl: './listar-pessoas.component.html',
  styleUrl: './listar-pessoas.component.css'
})
export class ListarPessoasComponent implements OnInit {

  pessoas: Pessoa[]=[];

  constructor(
    private pessoaService: PessoaService,
  ){}

  ngOnInit(): void {
    this.carregarListaDePessoasCadastradas();
  }

  public clickDeletarPessoa(id: any){
    this.deletarPessoa(id);
  }  

  public carregarListaDePessoasCadastradas(){
    this.pessoaService.listarPessoas().subscribe((result)=>{
      this.pessoas = result;
    });
  }

  public deletarPessoa(id: any){
    this.pessoaService.deletarPessoa(id).subscribe({
      next: (response) => {
        console.info("Deletado");
        alert('Deletado com sucesso!');
        window.location.reload();
      },
      error: (err) => {
        console.error('Erro ao deletar: ', err);
        alert('Erro ao deletar');
      }
    }); 
  }

}
