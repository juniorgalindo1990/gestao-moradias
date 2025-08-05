import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StudentService } from '../../../services/student.service';
import { Student } from '../../../models/student.model';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-student-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './student-details.component.html',
  styleUrls: ['./student-details.component.css']
})
export class StudentDetailsComponent implements OnInit {
  student: Student | null = null;
  private route = inject(ActivatedRoute);
  private studentService = inject(StudentService);

  ngOnInit(): void {
    const id = this.route.snapshot.params['id'];
    if (id) {
      this.studentService.getStudent(id).subscribe(data => {
        this.student = data;
      });
    }
  }
}
