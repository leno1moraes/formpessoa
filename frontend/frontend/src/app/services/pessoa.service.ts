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
    return this.http.get<Pessoa[]>(this.apiUrl + 'pessoas');
  }  

  cadastrarPessoa(pessoa: Pessoa): Observable<Pessoa>{
    return this.http.post<Pessoa>(this.apiUrl + 'pessoas', pessoa);
  }

  deletarPessoa(id: number): Observable<void> {
    return this.http.delete<void>(this.apiUrl + 'pessoas/' + id.toString());
  }

  acharPessoa(id: number): Observable<Pessoa> {
    return this.http.get<Pessoa>(this.apiUrl + 'pessoas/' + id.toString()); 
  }

  atualizarPessoa(id: number, pessoa: Pessoa): Observable<Pessoa>{
    return this.http.put<Pessoa>(this.apiUrl + 'pessoas/' + id.toString(), pessoa);
  }

}
