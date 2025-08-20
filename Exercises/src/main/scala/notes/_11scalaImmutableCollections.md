# Complete Scala Immutable Collections Hierarchy Reference

## 🌳 COLLECTION HIERARCHY TREE

```Each trait contains common characters/methods theru sub classes woudl use it```
```
Iterable (trait) - Base for all collections that can be iterated
│
├── Seq (trait) - Sequential collections with defined order and indexed access
│   │
│   ├── IndexedSeq (trait) - Fast random access O(1) or O(log n)
│   │   │
│   │   ├── Vector (class) ⭐ DEFAULT CHOICE
│   │   │   Purpose: General-purpose indexed sequence, uses trie data structure with 32 branch tree
│   │   │   Use Case: Most scenarios, balanced performance
│   │   │   When: Default choice unless specific needs
│   │   │   Performance: Access O(log₃₂n)≈O(1), Update O(log₃₂n)≈O(1)
│   │   │
│   │   ├── Range (class)
│   │   │   Purpose: Lazy arithmetic sequences
│   │   │   Use Case: Number sequences (1 to 100, 0 until 50)
│   │   │   When: Working with numeric ranges
│   │   │   Performance: Access O(1), Memory O(1) - lazy!
│   │   │
│   │   └── ArraySeq (class)
│   │       Purpose: Immutable wrapper around arrays
│   │       Use Case: When you have arrays but need immutable interface
│   │       When: Interop with Java arrays
│   │       Performance: Access O(1), Update O(n) - copies array
│   │
│   └── LinearSeq (trait) - Sequential access, optimized for head operations
│       │
│       ├── List (class) ⭐ FUNCTIONAL FAVORITE
│       │   Purpose: Singly-linked list, recursive processing
│       │   Use Case: Pattern matching, recursive algorithms, head operations
│       │   When: Heavy head manipulation, functional programming
│       │   Performance: Head O(1), Access O(n), Tail operations O(n)
│       │
│       ├── LazyList (class)
│       │   Purpose: Lazy evaluation, potentially infinite sequences
│       │   Use Case: Streams, infinite data, expensive computations
│       │   When: Need lazy evaluation or infinite sequences
│       │   Performance: Head O(1), lazy evaluation, can be infinite
│       │
│       └── Queue (class)
│       │   Purpose: FIFO (First In, First Out) operations
│       │   Use Case: Queue operations, breadth-first algorithms
│       │   When: Need efficient enqueue/dequeue
│       │   Performance: Enqueue O(1), Dequeue O(1)
│       │
│       └── Stack (class) [Deprecated - use List instead]
│           Purpose: LIFO operations
│           Use Case: Stack operations (deprecated)
│           When: DON'T USE - use List with :: and head/tail
│
├── Set (trait) - Collections with unique elements, no duplicates
│   │
│   ├── HashSet (class) ⭐ DEFAULT SET
│   │   Purpose: Hash-based set with fast lookups
│   │   Use Case: Uniqueness checking, membership tests
│   │   When: Need fast contains() operations
│   │   Performance: Add O(1), Contains O(1), Remove O(1)
│   │
│   ├── TreeSet (class)
│   │   Purpose: Sorted set using binary search tree
│   │   Use Case: Sorted unique elements, range queries
│   │   When: Need sorted iteration or range operations
│   │   Performance: Add O(log n), Contains O(log n), Ordered iteration
│   │
│   ├── ListSet (class)
│   │   Purpose: Set that preserves insertion order
│   │   Use Case: Small sets where insertion order matters
│   │   When: Small collections (<100 elements) with order
│   │   Performance: Add O(n), Contains O(n) - avoid for large sets
│   │
│   └── BitSet (class)
│       Purpose: Efficient set of small non-negative integers
│       Use Case: Sets of small integers (flags, permissions)
│       When: Working with integer flags/bits
│       Performance: Very fast for small integers, memory efficient
│
└── Map (trait) - Key-value associations
    │
    ├── HashMap (class) ⭐ DEFAULT MAP  
    │   Purpose: Hash-based key-value storage
    │   Use Case: Fast lookups, caching, indexing
    │   When: Need fast key-based access
    │   Performance: Get O(1), Put O(1), Remove O(1)
    │
    ├── TreeMap (class)
    │   Purpose: Sorted map using binary search tree
    │   Use Case: Sorted key iteration, range queries
    │   When: Need keys in sorted order
    │   Performance: Get O(log n), Put O(log n), Ordered key iteration
    │
    ├── ListMap (class)
    │   Purpose: Map that preserves insertion order
    │   Use Case: Small maps where insertion order matters
    │   When: Small collections with insertion order
    │   Performance: Get O(n), Put O(n) - avoid for large maps
    │
    └── VectorMap (class)
        Purpose: Map with insertion order, Vector-backed
        Use Case: Larger maps where insertion order matters
        When: Need insertion order with better performance than ListMap
        Performance: Get O(log n), Put O(log n), preserves order
```

## 📊 PERFORMANCE CHARACTERISTICS TABLE

| Collection | Type | Access | Insert | Delete | Search | Memory | Ordering |
|------------|------|--------|--------|--------|--------|--------|----------|
| **Vector** | IndexedSeq | O(log₃₂n)≈O(1) | O(log₃₂n)≈O(1) | O(log₃₂n)≈O(1) | O(n) | Excellent | Insertion |
| **List** | LinearSeq | O(n) | O(1) head | O(1) head | O(n) | Excellent | Insertion |
| **Range** | IndexedSeq | O(1) | N/A | N/A | O(n) | O(1) | Natural |
| **ArraySeq** | IndexedSeq | O(1) | O(n) | O(n) | O(n) | Good | Insertion |
| **LazyList** | LinearSeq | O(n) | O(1) head | O(1) head | O(n) | Lazy | Insertion |
| **Queue** | LinearSeq | O(n) | O(1) | O(1) | O(n) | Good | FIFO |
| **HashMap** | Map | O(1) avg | O(1) avg | O(1) avg | O(1) avg | Good | None |
| **TreeMap** | Map | O(log n) | O(log n) | O(log n) | O(log n) | Good | Sorted keys |
| **ListMap** | Map | O(n) | O(n) | O(n) | O(n) | Good | Insertion |
| **VectorMap** | Map | O(log n) | O(log n) | O(log n) | O(log n) | Good | Insertion |
| **HashSet** | Set | N/A | O(1) avg | O(1) avg | O(1) avg | Good | None |
| **TreeSet** | Set | N/A | O(log n) | O(log n) | O(log n) | Good | Sorted |
| **ListSet** | Set | N/A | O(n) | O(n) | O(n) | Good | Insertion |
| **BitSet** | Set | N/A | O(1) | O(1) | O(1) | Excellent | Sorted |

## 🎯 DECISION TREE: WHICH COLLECTION TO USE?

```
START: What do you need?

├─ Key-Value pairs?
│  ├─ YES → MAP
│  │  ├─ Fast lookup? → HashMap ⭐
│  │  ├─ Sorted keys? → TreeMap
│  │  └─ Insertion order? → VectorMap
│  └─ NO ↓

├─ Unique elements only?
│  ├─ YES → SET
│  │  ├─ Fast membership? → HashSet ⭐
│  │  ├─ Sorted elements? → TreeSet
│  │  ├─ Small integers? → BitSet
│  │  └─ Insertion order? → ListSet
│  └─ NO ↓

├─ Ordered sequence?
│  ├─ YES → SEQ
│  │  ├─ Heavy head operations? → List ⭐
│  │  ├─ Fast random access? → Vector ⭐
│  │  ├─ Lazy evaluation? → LazyList
│  │  ├─ FIFO operations? → Queue
│  │  └─ Number ranges? → Range
│  └─ NO → Reconsider if you need a collection
```

## 🏆 THE BIG THREE (90% of use cases)

### 1. Vector ⭐ - The Swiss Army Knife
```scala
val v = Vector(1, 2, 3, 4, 5)
// Fast everything: access, update, append, prepend
v(2)              // O(1) access
v :+ 6            // O(1) append  
0 +: v            // O(1) prepend
v.updated(2, 99)  // O(1) update
```

### 2. List ⭐ - The Functional Hero
```scala
val list = List(1, 2, 3, 4, 5)
// Lightning fast head operations
list.head         // O(1) 
0 :: list         // O(1) prepend
list match {
  case head :: tail => // Perfect for pattern matching
}
```

### 3. HashMap ⭐ - The Lookup Champion
```scala
val map = Map("a" -> 1, "b" -> 2)
// Fast key-value operations
map("a")          // O(1) lookup
map + ("c" -> 3)  // O(1) add
map.contains("a") // O(1) membership
```

## 🧠 MEMORY TRICKS TO REMEMBER

### **IndexedSeq vs LinearSeq**
- **IndexedSeq**: Think "**I**ndex" → **I** can jump to any position quickly
- **LinearSeq**: Think "**L**inear" → **L**ike a line, must walk through sequentially

### **Collection Names Tell Their Story**
- **Hash**Set/Map → **Hash** table → Fast O(1) operations
- **Tree**Set/Map → **Tree** structure → Sorted O(log n) operations  
- **List**Set/Map → **List** based → Preserves order but O(n) operations
- **Vector** → **V**ersatile → Good at everything
- **Lazy**List → **Lazy** evaluation → Computes only when needed

### **The -Seq Suffix Pattern**
- **IndexedSeq** → Fast random access (like arrays)
- **LinearSeq** → Sequential access (like linked lists)

## 📝 QUICK REFERENCE CODE PATTERNS

```scala
// CREATION PATTERNS
Vector(1, 2, 3)              // Direct construction
Vector.empty[Int]            // Empty vector
Vector.fill(5)(0)            // Vector(0, 0, 0, 0, 0)
Vector.tabulate(5)(_ * 2)    // Vector(0, 2, 4, 6, 8)

List(1, 2, 3)                // Direct construction  
1 :: 2 :: 3 :: Nil           // Cons construction
List.fill(3)("x")            // List("x", "x", "x")

Map("a" -> 1, "b" -> 2)      // Direct construction
Map.empty[String, Int]       // Empty map

Set(1, 2, 3)                 // Direct construction
Set.empty[Int]               // Empty set

// CONVERSION PATTERNS
someList.toVector            // List → Vector
someVector.toSet             // Vector → Set  
someMap.toList               // Map → List[(K,V)]
(1 to 10).toVector           // Range → Vector

// COMMON OPERATION PATTERNS
collection.map(f)            // Transform elements
collection.filter(p)        // Select elements
collection.fold(zero)(op)    // Aggregate
collection.groupBy(f)        // Group by function result
```

## ⚡ PERFORMANCE TIPS

1. **Default Choices**: Vector, HashMap, HashSet for 90% of cases
2. **Use List for**: Pattern matching, recursive algorithms, heavy head operations
3. **Use TreeMap/TreeSet for**: When you need sorted iteration
4. **Avoid ListMap/ListSet for**: Large collections (>100 elements)
5. **Use Range for**: Number sequences instead of Vector(1,2,3,...)
6. **Use LazyList for**: Expensive computations or infinite sequences
7. **Use builders for**: Creating large collections incrementally

---
*⭐ = Most commonly used, recommended as default choice*
*This reference covers scala.collection.immutable collections only*