import {Injectable} from "@angular/core";
import {Observable, of} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {catchError} from "rxjs/operators";

@Injectable({
  providedIn: "root"
})

export class LoginService{
  private loginUrl: string = "http://localhost:8080/login";

  constructor(private http: HttpClient) {
  }

  login(name: string, password: string): Observable<any>{
    return this.http.post<any>(this.loginUrl, {name: name, password: password})
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
