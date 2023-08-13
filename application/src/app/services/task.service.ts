import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Task} from "../task";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})

export class TaskService{

  private tasksUrl = '/api/tasks';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
      'Accept': 'application/json'
    })

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

  deleteTask(id: number): Observable<any>{
    return this.http.delete<Task>(`${this.tasksUrl}/${id}`)
      .pipe(
        catchError(this.handleError<Task>('DeleteTask' ))
      )
  }

  editTask(task: Task): Observable<Task>{
    return this.http.put<Task>(`${this.tasksUrl}/${task.id}`, task, this.httpOptions)
      .pipe(
        catchError(this.handleError<Task>('UpdateTask'))
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
