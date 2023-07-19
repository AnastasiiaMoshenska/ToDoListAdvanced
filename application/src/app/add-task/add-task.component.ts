import {Component, Input} from '@angular/core';
import {Category} from "../category";
import {CategoryService} from "../category.service";
import {TaskService} from "../task.service";
import {Task} from "../task";

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.scss']
})
export class AddTaskComponent {

  name: string = '';
  description: string = '';
  deadline: Date = new Date();
  categories: Category[] = [];
  category: Category = this.categories[0];
  newTask: Task = {id: 1, name: this.name, description: this.description, deadline: this.deadline, category: this.category};

  constructor(private categoryService: CategoryService, private taskService: TaskService) {
  }

  ngOnInit(){
    this.getCategories();
  }

  public addTask(){
    this.newTask = {id: 1, name: this.name, description: this.description, deadline: this.deadline, category: this.category}
    this.taskService.addTask(this.newTask).subscribe(() => {});
  }

  getCategories(){
    this.categoryService.getCategories().subscribe(categories => {
      this.categories = categories
      this.category = this.categories[0];
    });
  }
}
