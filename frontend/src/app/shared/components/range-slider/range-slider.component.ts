import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-range-slider',
  templateUrl: './range-slider.component.html',
  styleUrls: ['./range-slider.component.scss']
})
export class RangeSliderComponent implements OnInit {
  @Input() minValue!: number;
  @Input() maxValue!: number;
  lowerBound!: number;
  higherBound!: number;
  @Output() minValueChange = new EventEmitter<number>();
  @Output() maxValueChange = new EventEmitter<number>();

  ngOnInit() {
    this.lowerBound = this.minValue;
    this.higherBound = this.maxValue;
  }

  onMinValueChange(event: any): void {
    this.minValue = Math.min(this.minValue, this.maxValue);
    this.minValueChange.emit(this.minValue);
  }

  onMaxValueChange(event: any): void {
    this.maxValue = Math.max(this.minValue, this.maxValue);
    this.maxValueChange.emit(this.maxValue);
  }
}
