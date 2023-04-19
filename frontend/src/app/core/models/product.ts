import { Description } from './description';
import { ProductType } from './product-type';
import { TechnicalDetail } from './technical-detail';

export interface Product {
  productId: number;
  productType: ProductType;
  technicalDetail: TechnicalDetail;
  description: Description;
  productBrand: string;
  certification: string;
}
