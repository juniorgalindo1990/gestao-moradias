import { Component, OnInit, inject } from '@angular/core';
import { StudentService } from '../../../services/student.service';
import { Student } from '../../../model/student.model';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {
  students: Student[] = [];
  filter = {
    wifi: false,
    garagem: false,
    mobiliado: false,
    banheiroPrivativo: false
  };
  private studentService = inject(StudentService);
  private router = inject(Router);

  ngOnInit() {
    this.loadStudents();
  }

  loadStudents() {
    this.studentService.getStudents().subscribe(data => {
      this.students = data;
    });
  }

  applyFilters() {
    this.studentService.searchStudents(this.filter).subscribe(data => {
      this.students = data;
    });
  }

  viewDetails(id: number) {
    this.router.navigate(['/students', id]);
  }

  editStudent(id: number) {
    this.router.navigate(['/students/edit', id]);
  }

  deleteStudent(id: number) {
    if (confirm('Tem certeza que deseja excluir este estudante?')) {
      this.studentService.deleteStudent(id).subscribe(() => {
        this.loadStudents();
      });
    }
  }

  createStudent() {
    this.router.navigate(['/students/new']);
  }
}
