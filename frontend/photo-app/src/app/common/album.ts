export class Album {
    id: number;
    thumbnailUrl: string;
    name: string;
    description: string;

    constructor(id?: number, name?: string, description?: string, thumbnailUrl?: string) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnailUrl = thumbnailUrl;
    }

}
