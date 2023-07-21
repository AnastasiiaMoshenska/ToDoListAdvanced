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

  @Input() tasks!: Task[]
  constructor(private categoryService: CategoryService, private taskService: TaskService) {
  }

  ngOnInit(){
    this.getCategories();
  }

  public addTask() {
    if (this.name && this.description) {
      this.newTask = {
        id: this.tasks.length ? this.tasks[this.tasks.length - 1].id + 1 : 1,
        name: this.name,
        description: this.description,
        deadline: this.deadline,
        category: this.category
      }
      this.taskService.addTask(this.newTask).subscribe(() => {
        this.tasks.push(this.newTask);
      });
    }
  }

  getCategories(){
    this.categoryService.getCategories().subscribe(categories => {
      this.categories = categories
      this.category = this.categories[0];
    });
  }
}
