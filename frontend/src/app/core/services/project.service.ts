import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map, tap } from 'rxjs';
import { Project } from '../models/project';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  constructor(private http: HttpClient) {}

  getProjectIds$(username: string): Observable<number[]> {
    return this.http
      .get<Project[]>(
        `http://localhost:8081/joolemarketplace/projects/${username}`
      )
      .pipe(
        map((projects) => projects.map((project) => project.projectId)),
        tap((response) => console.log(response))
      );
  }
}
