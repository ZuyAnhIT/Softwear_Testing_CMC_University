describe('Cart and Checkout Functionality', () => {
  // Hàm này chạy trước mỗi bài test (để đỡ phải viết lại code đăng nhập)
  beforeEach(() => {
    cy.visit('https://www.saucedemo.com');
    cy.get('#user-name').type('standard_user');
    cy.get('#password').type('secret_sauce');
    cy.get('#login-button').click();
  });

  // Kịch bản 3: Thêm vào giỏ
  it('Should add a product to the cart', () => {
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    cy.get('.shopping_cart_badge').should('have.text', '1');
  });

  // Kịch bản 4: Sắp xếp giá
  it('Should sort products by price low to high', () => {
    cy.get('.product_sort_container').select('lohi'); // lohi = Low to High
    cy.get('.inventory_item_price').first().should('have.text', '$7.99');
  });

  // --- BÀI TẬP BỔ SUNG 1: Xóa sản phẩm ---
  it('Should remove a product from the cart', () => {
    // 1. Thêm sản phẩm trước
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    cy.get('.shopping_cart_badge').should('have.text', '1');
    
    // 2. Nhấn nút Remove (Nút Add ban đầu đã đổi thành Remove)
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    
    // 3. Kiểm tra badge số lượng biến mất
    cy.get('.shopping_cart_badge').should('not.exist');
  });

  // --- BÀI TẬP BỔ SUNG 2: Quy trình thanh toán ---
  it('Should complete the checkout process', () => {
    // 1. Thêm sản phẩm
    cy.get('.inventory_item').first().find('.btn_inventory').click();
    
    // 2. Vào giỏ hàng
    cy.get('.shopping_cart_link').click();
    
    // 3. Nhấn Checkout
    cy.get('#checkout').click();
    
    // 4. Điền thông tin
    cy.get('#first-name').type('John');
    cy.get('#last-name').type('Doe');
    cy.get('#postal-code').type('12345');
    
    // 5. Nhấn Continue
    cy.get('#continue').click();
    
    // 6. Xác minh chuyển trang thành công
    cy.url().should('include', '/checkout-step-two.html');
    cy.get('.title').should('contain', 'Checkout: Overview');
  });
});