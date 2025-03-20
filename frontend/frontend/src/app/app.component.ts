import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CadastrarPessoasComponent } from './components/cadastrar-pessoas/cadastrar-pessoas.component';
import { ListarPessoasComponent } from './components/listar-pessoas/listar-pessoas.component';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, CadastrarPessoasComponent, ListarPessoasComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
