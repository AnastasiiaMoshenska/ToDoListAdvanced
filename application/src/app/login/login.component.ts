import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {LoginService} from "../services/login.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit{

  model: any = {};
  sessionId: any = "";
  isHidden: boolean = true;

  constructor(
    private router: Router,
    private http: HttpClient,
    private loginService: LoginService

  ) {}

  ngOnInit() {
  }

  login(){
    this.loginService.login(this.model.userName, this.model.password).subscribe(res => {
        if (res != 0) {
          this.sessionId = res.sessionId;
          sessionStorage.setItem(
            'token',
            this.sessionId
          );
          this.router.navigate(['']);
        } else {
          this.isHidden = false
        }
      }
    )
  }
}
