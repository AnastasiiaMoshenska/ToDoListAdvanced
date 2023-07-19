import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of, tap} from "rxjs";
import {Task} from "./task";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})

export class TaskService{

  private tasksUrl = 'http://localhost:8080/tasks';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })

  };

  constructor(private http: HttpClient) {
  }

  getTasks(): Observable<Task[]>{
    return this.http.get<Task[]>(this.tasksUrl)
      .pipe(
        catchError(this.handleError<Task[]>('getTasks', []))
      )
  }

  addTask(task: Task): Observable<Task>{
    return <Observable<Task>> this.http.post<Task>(this.tasksUrl, task)
      .pipe(
        catchError(this.handleError<Task[]>('AddTask', []))
      )
  }

  private handleError<T>(operation = 'operation', result?: T){
    return (error: any): Observable<T> => {
      console.error(error);
      console.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    }
  }
}
