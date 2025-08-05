import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from '../../../services/student.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-student-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './student-form.component.html',
  styleUrls: ['./student-form.component.css']
})
export class StudentFormComponent implements OnInit {
  studentForm: FormGroup;
  isEditMode = false;
  studentId: number | null = null;

  private fb = inject(FormBuilder);
  private studentService = inject(StudentService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  constructor() {
    this.studentForm = this.fb.group({
      nomeCompleto: ['', Validators.required],
      cpf: ['', Validators.required],
      dataNascimento: ['', Validators.required],
      telefone: ['', Validators.required],
      curso: ['', Validators.required],
      periodoAtual: ['', Validators.required],
      wifi: [false],
      garagem: [false],
      mobiliado: [false],
      banheiroPrivativo: [false]
    });
  }

  ngOnInit(): void {
    this.studentId = this.route.snapshot.params['id'];
    if (this.studentId) {
      this.isEditMode = true;
      this.studentService.getStudent(this.studentId).subscribe(data => {
        this.studentForm.patchValue(data);
      });
    }
  }

  onSubmit(): void {
    if (this.studentForm.invalid) {
      return;
    }

    const studentData = this.studentForm.value;
    if (this.isEditMode && this.studentId) {
      this.studentService.updateStudent(this.studentId, studentData).subscribe(() => {
        this.router.navigate(['/students']);
      });
    } else {
      this.studentService.createStudent(studentData).subscribe(() => {
        this.router.navigate(['/students']);
      });
    }
  }
}
