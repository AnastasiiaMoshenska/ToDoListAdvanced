import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Task} from "../task";
import {CategoryService} from "../category.service";
import {Category} from "../category";
import {TaskService} from "../task.service";

@Component({
  selector: '[app-task]',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent {
  @Input() task!: Task;
  @Input() tasks!: Task[];
  @Input() categories!: Category[];
  @Output() tasksChange = new EventEmitter<Task[]>();
  editAction: string = "Edit"
  isReadOnly: boolean = true

  constructor(private taskService: TaskService) {
  }

  ngInit(): void{

  }

  public deleteTask(id: number){
    this.taskService.deleteTask(id).subscribe(() => {
      this.tasks = this.tasks.filter(task => task.id !== id);
      this.tasksChange.emit(this.tasks);
    })
  }

  public editTask(id: number) {
    console.log(this.task)
    if (this.editAction == "Edit") {
      this.editAction = "Save"
      this.isReadOnly = false
    } else if(this.task.name && this.task.description){
      this.taskService.editTask(this.task).subscribe(
        () => {
          this.editAction = "Edit"
          this.isReadOnly = true
        }
      );
    } else {

    }
  }
}
