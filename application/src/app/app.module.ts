import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { SettingsComponent } from './settings/settings.component';
import { TaskComponent } from './task/task.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { AddTaskComponent } from './add-task/add-task.component';
import {FormsModule} from "@angular/forms";
import { LoginComponent } from './login/login.component';
import {RequestInterceptor} from "./request.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    SettingsComponent,
    TaskComponent,
    AddTaskComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: RequestInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
