import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-product-create',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.scss']
})
export class ProductCreateComponent {
  product = {
    name: '',
    quantity: 0,
    price: 0
  };

  constructor(private router: Router) { }

  onSubmit() {
    // Aqui você poderá chamar um serviço para salvar o produto
    console.log('Produto cadastrado:', this.product);
    this.router.navigate(['/products']);
  }
}
