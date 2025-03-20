import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Pessoa } from '../models/pessoa.model';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  private apiUrl = 'http://localhost:8081/api/';

  constructor(private http: HttpClient) {}
  
  listarPessoas(): Observable<Pessoa[]> {
    //console.log("link listarPessoas: ", this.apiUrl + 'pessoas');
    return this.http.get<Pessoa[]>(this.apiUrl + 'pessoas');
  }  

  cadastrarPessoa(pessoa: Pessoa): Observable<Pessoa>{
    return this.http.post<Pessoa>(this.apiUrl + 'pessoas', pessoa);
  }
}
