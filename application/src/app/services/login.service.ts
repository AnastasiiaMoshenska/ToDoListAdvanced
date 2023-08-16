import {Injectable} from "@angular/core";
import {Observable, of} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})

export class LoginService{

  private url = '/api/login';

  constructor(private http: HttpClient) {
  }

  login(userName: String, password: String){
    return this.http.post<any>(this.url, {
      userName: userName,
      password: password
    })
      .pipe(
        catchError(this.handleError<any>("login", []))
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
