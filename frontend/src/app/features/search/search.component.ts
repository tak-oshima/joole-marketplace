import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';
import { map, shareReplay, tap } from 'rxjs/operators';
import { Product } from 'src/app/core/models/product';
import { ProductService } from 'src/app/core/services/product.service';
import { ProjectService } from 'src/app/core/services/project.service';
import { UserService } from 'src/app/core/services/user.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
})
export class SearchComponent implements OnInit {
  projectIds$!: Observable<number[]>;
  productBrands$!: Observable<string[]>;

  products$!: Observable<Product[]>;
  productBrandsDistinct!: string[];

  selectedProjectId: number | null = null;
  productBrandInput: string = '';
  isOptionsVisible: boolean = false;

  constructor(
    private userService: UserService,
    private projectService: ProjectService,
    private productService: ProductService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const username: string = this.userService.username!;
    this.projectIds$ = this.projectService.getProjectIds$(username).pipe(
      tap((projectIds) => {
        if (projectIds.length > 0) {
          this.selectedProjectId = projectIds[0];
          this.updateBrands();
        }
      })
    );
  }

  onProjectChange(): void {
    this.productBrandInput = '';
    this.isOptionsVisible = false;
    this.updateBrands();
  }

  onInputChange(): void {
    this.isOptionsVisible = this.productBrandInput.length > 0;
  }

  onOptionSelect(option: string) {
    this.productBrandInput = option;
    this.isOptionsVisible = false;
  }

  onSearchClick(): void {
    this.router.navigate(['/product-list'], {
      queryParams: {
        projectId: this.selectedProjectId,
        productBrand: this.productBrandInput,
      },
    });
  }

  updateBrands(): void {
    this.productBrands$ = this.productService
      .getProducts$(this.selectedProjectId!)
      .pipe(
        map((products) => {
          const productBrands = products.map((product) => product.productBrand);
          return Array.from(new Set(productBrands));
        })
      );
  }
}
