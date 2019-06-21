var app = new Vue({
  el: '#app',
  data: {
    searchList:[],
  },
  mounted() {
    var url = new URL(location.href);
    var searchFactor = url.searchParams.get("searchFactor");
    this.getSerachList(searchFactor);
  },
  methods: {
    getSerachList(searchFactor) {
      axios.get('/search/search', {
          params: {
            searchFactor: searchFactor
          }
      })
          .then(function (response) {
              console.log(response);
              app.searchList = response.data;

          })
          .catch(function (error) {
              console.log(error);
          });
  }
  }
})