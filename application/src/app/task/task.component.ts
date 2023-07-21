import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Task} from "../task";
import {CategoryService} from "../category.service";
import {Category} from "../category";
import {TaskService} from "../task.service";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent {
  @Input() task!: Task;
  @Input() tasks!: Task[];
  @Output() tasksChange = new EventEmitter<Task[]>();

  protected readonly JSON = JSON;

  constructor(private taskService: TaskService) {
  }

  public deleteTask(id: number){
    this.taskService.deleteTask(id).subscribe(() => {
      this.tasks = this.tasks.filter(task => task.id !== id);
      this.tasksChange.emit(this.tasks);
    })
  }
}
