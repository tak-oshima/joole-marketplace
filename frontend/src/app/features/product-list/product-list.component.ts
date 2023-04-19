import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable, map } from 'rxjs';
import { AdvancedSearchRequest } from 'src/app/core/models/advanced-search-request';
import { Product } from 'src/app/core/models/product';
import { ProductService } from 'src/app/core/services/product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss'],
})
export class ProductListComponent implements OnInit {
  projectId!: number;
  productBrand!: string | null;
  products$!: Observable<Product[]>;

  minYear: number = 2020;
  maxYear: number = 2023;

  search: AdvancedSearchRequest = {
    productTypeSearchRequest: {
      application: "",
      type: "",
      mountingLocation: "",
      accessories: "",
      minModelYear: null,
      maxModelYear: null,
    },
    technicalDetailSearchRequest: {
      minAirflow: 2000,
      maxAirflow: 10000,
      minPower: 0,
      maxPower: 100,
      minOperatingVoltage: 100,
      maxOperatingVoltage: 220,
      minFanSpeed: 10,
      maxFanSpeed: 100,
    },
    productBrand: ""
  };

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService
  ) {}

  ngOnInit(): void {
    this.route.queryParamMap.subscribe((params) => {
      this.projectId = parseInt(params.get('projectId')!, 10);
      this.productBrand = params.get('productBrand');
      this.search.productBrand = this.productBrand ? this.productBrand : "";
      this.updateProducts();
    });
  }

  onSaveClick(): void {
    const request: AdvancedSearchRequest = this.preprocessSearchRequest(this.search);
    this.products$ = this.productService.getFilteredProducts$(request);
  }

  onClearClick(): void {
    location.reload();
  }

  updateProducts(): void {
    this.products$ = this.productService
      .getProducts$(this.projectId)
      .pipe(
        map((products) =>
          products.filter((product) => this.productBrand ? product.productBrand === this.productBrand : true)
        )
      );
  }

  preprocessSearchRequest(search: AdvancedSearchRequest): AdvancedSearchRequest {
    const processedSearch = {...search};
    if (search.productTypeSearchRequest.application === "") {
      processedSearch.productTypeSearchRequest.application = null;
    }
    if (search.productTypeSearchRequest.type === "") {
      processedSearch.productTypeSearchRequest.type = null;
    }
    if (search.productTypeSearchRequest.mountingLocation === "") {
      processedSearch.productTypeSearchRequest.mountingLocation = null;
    }
    if (search.productTypeSearchRequest.accessories === "") {
      processedSearch.productTypeSearchRequest.accessories = null;
    }

    processedSearch.productTypeSearchRequest.minModelYear = new Date(this.minYear, 0, 1);
    processedSearch.productTypeSearchRequest.maxModelYear = new Date(this.maxYear, 11, 31);

    return processedSearch;
  }
}
