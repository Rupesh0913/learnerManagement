<h4>Why I used ArrayList instead of array ?</h4>

I used ArrayList instead of array because ArrayList provides dynamic sizing, allowing the collection to grow and shrink as needed. This flexibility is essential for managing entities like students, trainers, courses, and enrollments, where the number of items can vary significantly. Additionally, ArrayList offers built-in methods for common operations such as adding, removing, and searching for elements, which simplifies code and enhances readability compared to using traditional arrays.

<H4>Where I used static members and why ?</H4>
Static members are used in the service implementation classes to maintain a single instance of the data storage (ArrayLists) for each entity type (students, trainers, courses, enrollments). This approach ensures that all instances of the service classes share the same data, allowing for consistent management of entities across different parts of the application. By using static members, we avoid the need to pass around data collections between different service instances, simplifying the code and improving performance.
I also use it in the ID generation for entities to ensure unique identifiers across all instances.

<H4>Where I used inheritance and what I gained from it ?</H4>
Inheritance is used in the entity classes where `Student` and `Trainer` classes inherit from the base class `Person`. This allows both `Student` and `Trainer` to share common attributes such as `id`, `name`, and `email`, reducing code duplication and promoting code reusability. By inheriting from `Person`, we can easily extend the functionality of both `Student` and `Trainer` while maintaining a clear and organized class hierarchy. This also simplifies future enhancements, as changes to common attributes or methods can be made in the base class and automatically reflected in the derived classes.
