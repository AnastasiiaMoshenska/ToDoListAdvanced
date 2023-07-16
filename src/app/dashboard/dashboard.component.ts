import { Component } from '@angular/core';
import {TASKS} from "../mock-list";
import {Task} from "../task";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent {
  tasks: Task[] = TASKS;
}
