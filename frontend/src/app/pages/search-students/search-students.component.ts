import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { StudentService } from '../../services/student.service';

@Component({
  selector: 'app-search-students',
  standalone: true, 
  imports: [CommonModule, FormsModule], 
  templateUrl: './search-students.component.html',
  styleUrls: ['./search-students.component.css'],

})
export class SearchStudentsComponent {
  filtros = {
  preferenciaMoradia: '',
  aceitaPets: false,
  precisaMobilidadeReduzida: false
};


  estudantes: any[] = [];

  constructor(private studentService: StudentService) {}
  buscarEstudantes() {
    console.log('Enviando filtros:', this.filtros);
    this.studentService.buscarEstudantes(this.filtros).subscribe({
      next: (dados) => {
        this.estudantes = dados;
      },
      error: (err) => {
        console.error('Erro ao buscar estudantes:', err);
      }
    });
  }
}
