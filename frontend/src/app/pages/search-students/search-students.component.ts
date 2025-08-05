import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms'; 
import { StudentService } from '../../services/student.service';
import { Student } from '../../model/student.model';

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

  estudantes: Student[] = []; 

  constructor(private studentService: StudentService) {}

  buscarEstudantes() {
    console.log('Enviando filtros:', this.filtros);
   
    this.studentService.searchStudents(this.filtros).subscribe({
      next: (dados: Student[]) => { 
        this.estudantes = dados;
      },
      error: (err: any) => {
        console.error('Erro ao buscar estudantes:', err);
      }
    });
  }
}