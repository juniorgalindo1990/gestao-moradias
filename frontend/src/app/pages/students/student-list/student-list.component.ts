import { Component, OnInit, inject } from '@angular/core';
import { StudentService } from '../../../services/student.service';
import { Student } from '../../../models/student.model';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {
  students: Student[] = [];
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
