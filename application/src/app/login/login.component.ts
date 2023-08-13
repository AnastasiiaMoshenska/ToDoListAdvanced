import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginService} from "../services/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private loginService: LoginService) {}

  login() {
    this.loginService.login(this.username, this.password).subscribe(() => {
      console.log("LOGIN TRY");
    });
  }
}
