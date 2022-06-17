package com.alex.kotlinmovies.view.favorites

//
//class FavoriteMoviesActivity : AppCompatActivity(), MovieAdapter.ItemClickListener {
//
//    private lateinit var mViewModel: MoviesViewModel
//    private lateinit var mRcFavView: RecyclerView
//    private lateinit var mBinding: ActivityFavoriteMoviesBinding
//    private lateinit var mMoviesAdapter: MovieAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        mBinding = ActivityFavoriteMoviesBinding.inflate(layoutInflater)
//        setContentView(mBinding.root)
//        initViews()
//        initObservers()
//        mViewModel.getMovies()
//        setupBottomMenu()
//    }
//
//    private fun initObservers() {
//        mViewModel.apply {
//            movies.observe(this@FavoriteMoviesActivity) {
////                mMoviesAdapter = MovieAdapter(it, this@FavoriteMoviesActivity)
//                mRcFavView.adapter = mMoviesAdapter
//            }
//        }
//    }
//
//    private fun initViews() {
//        mRcFavView = mBinding.rvFavMovies
//        val layoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
//        mRcFavView.layoutManager = layoutManager
//        mViewModel = MoviesViewModel(RetrofitRepository())
//    }
//
//    override fun onBackPressed() {
//        super.onBackPressed()
//        this.finishAffinity()
//    }
//
//    override fun onItemClick(position: Int) {
//        val intent = Intent(this@FavoriteMoviesActivity, MovieDetailsActivity::class.java)
//        intent.putExtra("id", position)
//        startActivity(intent)
//    }
//
//    private fun setupBottomMenu() {
//        mBinding.bottomNavigation.setOnItemSelectedListener() {
//            when (it.itemId) {
//                R.id.action_discover -> {
//                    Intent(this, MoviesActivity::class.java)
//                }
//                R.id.action_favorites -> {
//                    Intent(this, FavoriteMoviesActivity::class.java)
//                }
//            }
//            false
//        }
//    }
//}