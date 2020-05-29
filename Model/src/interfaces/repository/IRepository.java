package interfaces.repository;

public interface IRepository<Entity> {
    Entity insert(Entity entity);
}
