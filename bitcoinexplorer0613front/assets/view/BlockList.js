var app = new Vue({
  el: '#app',
  data: {
    blockList: [],
    transactionList: [],
    searchFactor:'',
  },

  computed: {
    showblockList() {
      var now = Date.now();
      this.blockList.forEach(block => {
        block.time = parseInt((now - block.time) / 1000 / 60) + " minutes";
      });
      return this.blockList;
    },
    showtransactionList() {
      var now = Date.now();
      this.transactionList.forEach(block => {
        block.time = parseInt((now - block.time) / 1000 / 60) + " minutes";
      });
      return this.transactionList;
    },

  },
  mounted() {
    this.getBlockList();
    this.getTransactionList();
  },
  methods: {
    getTransactionList() {
      axios.get('/transaction/getTransactions')
        .then(function (response) {
          // handle success
          console.log(response);
          app.transactionList = response.data;
        })
        .catch(function (error) {
          // handle error
          console.log(error);
        });
    },
    getBlockList() {
      axios.get('/block/getBlocks')
        .then(function (response) {
          // handle success
          console.log(response);
          app.blockList = response.data;
        })
        .catch(function (error) {
          // handle error
          console.log(error);
        });
    },
    handleSearch(){
      location.href='SearchList.html?searchFactor='+this.searchFactor;
    }
  }
})