import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, shareReplay } from 'rxjs';
import { Product } from '../models/product';
import { AdvancedSearchRequest } from '../models/advanced-search-request';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  currProjectId: number | null = null;
  products$: Observable<Product[]> | null = null;

  constructor(private http: HttpClient) {}

  getProducts$(projectId: number): Observable<Product[]> {
    if (this.currProjectId === projectId && this.products$) {
      return this.products$;
    } else {
      this.currProjectId = projectId;
      return (this.products$ = this.http
        .get<Product[]>(
          `http://localhost:8081/joolemarketplace/products/project/${projectId}`
        )
        .pipe(shareReplay(1)));
    }
  }

  getFilteredProducts$(request: AdvancedSearchRequest): Observable<Product[]> {
    return this.http.post<Product[]>(
      `http://localhost:8081/joolemarketplace/products/search`,
      request
    );
  }
}
