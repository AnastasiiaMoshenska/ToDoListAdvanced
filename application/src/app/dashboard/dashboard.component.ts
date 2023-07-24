import { Component } from '@angular/core';
import {Task} from "../task";
import {TaskService} from "../task.service";
import {CategoryService} from "../category.service";
import {Category} from "../category";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  tasks: Task[] = [];
  categories: Category[] = [];
  category: Category = this.categories[0];

  constructor(private taskService: TaskService, private categoryService: CategoryService,) {
  }

  ngOnInit(){
    this.getTasks();
    this.getCategories();
  }

  getTasks(){
    this.taskService.getTasks().subscribe(tasks => this.tasks = tasks);
  }

  getCategories(){
    this.categoryService.getCategories().subscribe(categories => {
      this.categories = categories
      this.category = this.categories[0];
    });
  }
}
