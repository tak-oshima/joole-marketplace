import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { RouterModule } from '@angular/router';
import { LogoLargeComponent } from './components/logo-large/logo-large.component';
import { SearchBarComponent } from './components/search-bar/search-bar.component';
import { FormsModule } from '@angular/forms';
import {
  NgbDropdownModule,
  NgbTooltipModule,
} from '@ng-bootstrap/ng-bootstrap';
import { RangeSliderComponent } from './components/range-slider/range-slider.component';
import { MatSliderModule } from '@angular/material/slider';

@NgModule({
  declarations: [
    HeaderComponent,
    LogoLargeComponent,
    SearchBarComponent,
    RangeSliderComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    NgbDropdownModule,
    NgbTooltipModule,
    MatSliderModule,
  ],
  exports: [
    HeaderComponent,
    LogoLargeComponent,
    SearchBarComponent,
    RangeSliderComponent,
  ],
})
export class SharedModule {}
