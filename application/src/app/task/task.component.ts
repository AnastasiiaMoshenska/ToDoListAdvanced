import {Component, Input} from '@angular/core';
import {Task} from "../task";
import {CategoryService} from "../category.service";
import {Category} from "../category";

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.scss']
})
export class TaskComponent {
  @Input() task!: Task
  protected readonly JSON = JSON;
}
