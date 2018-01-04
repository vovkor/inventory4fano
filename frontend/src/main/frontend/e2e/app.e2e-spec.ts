import { Inventory4fanoPage } from './app.po';

describe('inventory4fano App', function() {
  let page: Inventory4fanoPage;

  beforeEach(() => {
    page = new Inventory4fanoPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('App works!');
  });
});
