package com.msc.middlestump

class Datasource {
    fun loadProducts(): List<products>{

        return listOf<products>(
            products(R.string.n1, R.drawable.n1),
            products(R.string.n2, R.drawable.n2),
            products(R.string.s1, R.drawable.s1),
            products(R.string.s2, R.drawable.s2),
            products(R.string.n1, R.drawable.n1),
            products(R.string.n2, R.drawable.n2),
            products(R.string.s1, R.drawable.s1),
            products(R.string.s2, R.drawable.s2),
            products(R.string.n1, R.drawable.n1),
            products(R.string.n2, R.drawable.n2),
            products(R.string.s1, R.drawable.s1),
            products(R.string.s2, R.drawable.s2),
            products(R.string.n1, R.drawable.n1),
            products(R.string.n2, R.drawable.n2),
            products(R.string.s1, R.drawable.s1),
            products(R.string.s2, R.drawable.s2)
        )
    }
}