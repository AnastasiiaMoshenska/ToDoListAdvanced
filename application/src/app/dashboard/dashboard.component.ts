import { Component } from '@angular/core';
import {Task} from "../task";
import {TaskService} from "../task.service";
import {CategoryService} from "../category.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  tasks: Task[] = [];

  constructor(private taskService: TaskService) {
  }

  ngOnInit(){
    this.getTasks();
  }

  getTasks(){
    this.taskService.getTasks().subscribe(tasks => this.tasks = tasks);
  }
}
