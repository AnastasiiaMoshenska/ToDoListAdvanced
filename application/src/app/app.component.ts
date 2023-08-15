import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'ToDoList';

  constructor(private router: Router, private activatedRoute: ActivatedRoute) {}

  showNavBar(): boolean{
    const currentRoutePath = this.router.routerState.snapshot.url;
    return currentRoutePath !== '/login';
  }
}
