import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {catchError} from "rxjs/operators";
import {Category} from "../category";

@Injectable({
  providedIn: 'root'
})

export class CategoryService{

  private categoryUrl = '/api/categories';

  constructor(private http: HttpClient) {
  }

  getCategories(): Observable<Category[]>{
    return this.http.get<Category[]>(this.categoryUrl)
      .pipe(
        catchError(this.handleError<Category[]>('getCategories', []))
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
