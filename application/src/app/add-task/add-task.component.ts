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
  @Input() categories!: Category[];
  @Input() category!: Category;
  newTask: Task = {id: 1, name: this.name, description: this.description, deadline: this.deadline, category: this.category};

  @Input() tasks!: Task[]
  constructor(private taskService: TaskService) {
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
}
