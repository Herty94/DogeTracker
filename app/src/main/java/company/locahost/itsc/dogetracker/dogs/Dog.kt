package company.locahost.itsc.dogetracker.dogs

import java.util.*

class Dog {

    private var name: String
    private var breed: String?
    private var weight: Double?
    private var date: String?
    private var notes: String?

    constructor(name: String, breed: String?, weight: Double?, date: String?, notes: String?) {
        this.name = name
        this.breed = breed
        this.weight = weight
        this.date = date
        this.notes = notes

    }
}