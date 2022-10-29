describe('start with productsdashboard',()=>{

    it('visit productsdashboard',()=>
    
    {
        cy.visit("/")
    })

})

describe ('productsdashboard',()=>{

    it('should reach productsdashboard',()=>{
  
  cy.url().should('include','products');
  cy.wait(2000)
    })

    it('should check title',()=>{

        cy.get('.clsh').contains('Ubuysell')
        cy.wait(2000)
        cy.get('.fa-cart-plus').click()
        cy.wait(2000)
       
      })
      it('should reach login',()=>{

        cy.url().should('include','login');
        })
  })

  describe("checking login",()=>{

        it("shoudl enter value",()=>{

            cy.get(".cname").type("vaishnavi@gmail.com");
            cy.get(".cpass").type("pass123");
            cy.wait(3000)
            cy.get('.button').click();
    })

    it('should reach productsdashboard',()=>{

    cy.url().should('include','products');
})
})

describe("sellerdashboard",()=>{
    it("should reach sellerdashboard",()=>
    {
        cy.get('.fa-cog').click();
        cy.wait(3000)
        cy.get('.fa-sellcast').click();
        cy.wait(3000)
        cy.url().should('include','sell');
        cy.wait(2000)
        cy.get('.clsbtn').click();
        cy.url().should('include','intrested');
        cy.wait(2000)
    })
})

describe("logout",()=>{
    it("should logout",()=>
    {
        cy.get('.fa-cog').click();
        cy.wait(3000)
        cy.get('.fa-sign-out').click();
        cy.wait(3000)

    })
})

