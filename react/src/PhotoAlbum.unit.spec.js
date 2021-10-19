import { PhotoAlbum } from "./PhotoAlbum";
import { shallow } from "enzyme";
import { SearchInput } from "./SearchInput";

jest.mock("./common/fetchService");

describe("PhotoAlbum", () => {
    it("should render SearchInput component", async () => {
        const wrapper = shallow(<PhotoAlbum />);

        const searchInputComponent = wrapper.find("SearchInput");
        expect(searchInputComponent).toHaveLength(1);
    });

    it("should render Photos component with empty array", async () => {
        const wrapper = shallow(<PhotoAlbum />);

        const photosComponent = wrapper.find("Photos");
        expect(photosComponent).toHaveLength(1);
        expect(photosComponent.props().photos).toEqual([]);
    });
});
