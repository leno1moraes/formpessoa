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
  ){
    
  }

  ngOnInit(): void {
    this.carregarListaDePessoasCadastradas();
  }

  public carregarListaDePessoasCadastradas(){
    this.pessoaService.listarPessoas().subscribe((result)=>{
      this.pessoas = result;
    });

  }

}
