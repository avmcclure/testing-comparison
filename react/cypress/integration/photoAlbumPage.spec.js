describe("PhotoAlbum Page", () => {
    beforeEach(() => {
        cy.visit("/");
    });

    it("should return no photos when page loads", () => {
        const albumText = cy.findByText("Album ID: 1");
        albumText.should("not.exist");
        const albumImage = cy.findByAltText("Album ID 1");
        albumImage.should("not.exist");
    });

    it("should return photos when typing into search input and search button is clicked", async () => {
        const searchInput = cy.findByLabelText("Search:");
        searchInput.type("1");

        const searchButton = cy.findByRole("button", {
            name: "Search",
        });
        searchButton.click();

        const albumText = cy.findAllByText("Album ID: 1");
        albumText.should("exist");
        const albumImage = cy.findAllByAltText("Album ID 1");
        albumImage.should("exist");
    });
});
